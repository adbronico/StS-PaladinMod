package paladinmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class ModifyDexterityAction extends AbstractGameAction
{
    private final AbstractCreature owner;
    private final AbstractCreature target;

    public ModifyDexterityAction(AbstractCreature owner, int amount)
    {
        this.actionType = ActionType.WAIT;
        this.owner = owner;
        this.target = owner;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public ModifyDexterityAction(AbstractCreature owner, AbstractCreature target, int amount)
    {
        this.actionType = ActionType.WAIT;
        this.owner = owner;
        this.target = target;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update()
    {
        if(Settings.ACTION_DUR_XFAST == this.duration)
        {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.owner, new DexterityPower(this.target, this.amount), this.amount));
        }

        this.isDone = true;
    }
}
