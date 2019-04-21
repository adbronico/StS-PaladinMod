package paladinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import paladinmod.powers.DivinityPower;

public class ResetDivinityAction extends AbstractGameAction
{
    private final AbstractPlayer player;

    public ResetDivinityAction()
    {
        this.actionType = ActionType.WAIT;
        this.player = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update()
    {
        if(player.hasPower(DivinityPower.POWER_ID))
        {
            int divinityAmount = player.getPower(DivinityPower.POWER_ID).amount;
            if(divinityAmount > 0)
            {
                AbstractDungeon.actionManager.addToBottom(new LoseDivinityAction(divinityAmount));
            }
            else
            {
                AbstractDungeon.actionManager.addToBottom(new GainDivinityAction(-divinityAmount));
            }
        }

        this.isDone = true;
    }
}
