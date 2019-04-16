package paladinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.UUID;

public class ModifyHealAction extends AbstractGameAction
{
    private UUID uuid;

    public ModifyHealAction(UUID uuid, int amount)
    {
        this.setValues(this.target, this.source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.uuid = uuid;
    }

    @Override
    public void update()
    {
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group)
        {
            if(card.uuid.equals(this.uuid))
            {
                card.misc += this.amount;
                if(card.misc < 0)
                {
                    card.misc = 0;
                }
                card.applyPowers();
            }
        }

        for (AbstractCard card: GetAllInBattleInstances.get(this.uuid))
        {
            card.misc += this.amount;
            if(card.misc < 0)
            {
                card.misc = 0;
            }
            card.applyPowers();
        }

        this.isDone = true;
    }
}
