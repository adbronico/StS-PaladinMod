package paladinmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import paladinmod.PaladinMod;

public class DivinityPower extends AbstractPower
{
    public static final String POWER_ID = "PaladinMod:DivinityPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = "powers/Divinity.png";

    public DivinityPower(AbstractCreature owner, int amount)
    {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = PaladinMod.handleAmount(amount);
        this.description = DESCRIPTIONS[0];
        this.img = new Texture(PaladinMod.makePath(IMG));
        this.canGoNegative = true;
    }

    public void stackPower(int stackAmount)
    {
        this.fontScale = 8.0F;
        this.amount = PaladinMod.handleAmount(this.amount + stackAmount);

        if (this.amount == 0)
        {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
    }
}
