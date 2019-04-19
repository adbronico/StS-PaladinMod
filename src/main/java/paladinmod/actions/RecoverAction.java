package paladinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class RecoverAction extends AbstractGameAction
{
    private boolean freeToPlayOnce;
    private AbstractPlayer player;
    private int energyOnUse;

    public RecoverAction(AbstractPlayer player, int amount, boolean freeToPlayOnce, int energyOnUse)
    {
        this.amount = amount;
        this.player = player;
        this.freeToPlayOnce = freeToPlayOnce;
        this.actionType = ActionType.HEAL;
        this.energyOnUse = energyOnUse;
    }

    public void update()
    {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
        {
            effect = this.energyOnUse;
        }

        if (this.player.hasRelic("Chemical X"))
        {
            effect += 2;
            this.player.getRelic("Chemical X").flash();
        }

        if (effect > 0)
        {
            for (int i = 0; i < effect; ++i)
            {
                AbstractDungeon.actionManager.addToBottom(new HealAction(this.player, this.player, this.amount));
            }

            if (!this.freeToPlayOnce)
            {
                this.player.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;
    }
}
