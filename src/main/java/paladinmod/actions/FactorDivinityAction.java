package paladinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import paladinmod.powers.DivinityPower;

public class FactorDivinityAction extends AbstractGameAction
{
    private final AbstractPlayer player;
    private final float factor;

    public FactorDivinityAction(float factor)
    {
        this.actionType = ActionType.WAIT;
        this.player = AbstractDungeon.player;
        this.factor = factor;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update()
    {
        if(Settings.ACTION_DUR_XFAST == this.duration)
        {
            int divinityAmount = player.hasPower(DivinityPower.POWER_ID) ? player.getPower(DivinityPower.POWER_ID).amount : 0;
            int divinityToAdd = Math.round(divinityAmount * this.factor) - divinityAmount;
            AbstractDungeon.actionManager.addToBottom(new GainDivinityAction(divinityToAdd));
        }

        this.isDone = true;
    }
}
