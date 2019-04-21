package paladinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import paladinmod.powers.DivinityPower;
import paladinmod.powers.RedemptionPower;

public class LoseDivinityAction extends AbstractGameAction
{
    private final AbstractPlayer player;

    public LoseDivinityAction(int amount)
    {
        this.actionType = ActionType.WAIT;
        this.player = AbstractDungeon.player;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update()
    {
        if(!player.hasPower(RedemptionPower.POWER_ID))
        {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.player, this.player, new DivinityPower(this.player, -this.amount), -this.amount));
        }

        this.isDone = true;
    }
}