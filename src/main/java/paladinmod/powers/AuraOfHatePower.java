package paladinmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import paladinmod.PaladinMod;
import paladinmod.actions.ModifyStrengthAction;

public class AuraOfHatePower extends AbstractPower
{
    public static final String POWER_ID = "PaladinMod:AuraOfHatePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = "powers/Divinity.png";

    public AuraOfHatePower(AbstractCreature owner, int amount)
    {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = PaladinMod.handleAmount(amount);
        this.description = DESCRIPTIONS[0];
        this.img = new Texture(PaladinMod.makePath(IMG));
    }

    @Override
    public void atStartOfTurnPostDraw()
    {
        if(this.owner.hasPower(DivinityPower.POWER_ID) && this.owner.getPower(DivinityPower.POWER_ID).amount < 0)
        {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ModifyStrengthAction(this.owner, this.amount));
        }
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
