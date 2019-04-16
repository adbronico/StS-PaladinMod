package paladinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import paladinmod.powers.DivinityPower;

public class ResetDivinityAction extends AbstractGameAction
{
    private AbstractPlayer player;

    public ResetDivinityAction()
    {
        this.actionType = ActionType.WAIT;
        this.player = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update()
    {
        if(Settings.ACTION_DUR_XFAST == this.duration && player.hasPower(DivinityPower.POWER_ID))
        {
            int divinityAmount = player.getPower(DivinityPower.POWER_ID).amount;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player, new DivinityPower(this.player, -divinityAmount), -divinityAmount));
        }

        this.isDone = true;
    }
}
