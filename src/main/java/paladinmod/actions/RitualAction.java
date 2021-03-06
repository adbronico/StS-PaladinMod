package paladinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import paladinmod.cards.AbstractPaladinCard;

import java.util.Iterator;
import java.util.UUID;

public class RitualAction extends AbstractGameAction
{
    private UUID uuid;

    public RitualAction(UUID targetUUID)
    {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.uuid = targetUUID;
    }

    public void update()
    {
        Iterator cardIter = GetAllInBattleInstances.get(this.uuid).iterator();

        while (cardIter.hasNext())
        {
            AbstractCard abstractCard = (AbstractCard) cardIter.next();
            if(abstractCard instanceof  AbstractPaladinCard)
            {
                AbstractPaladinCard card = (AbstractPaladinCard) abstractCard;
                card.baseRitual -= 1;
                card.initializeDescription();

                if (card.baseRitual < 0)
                {
                    card.baseRitual = 0;
                }
            }
        }

        this.isDone = true;
    }
}
