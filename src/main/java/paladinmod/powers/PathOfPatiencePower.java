package paladinmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import paladinmod.PaladinMod;
import paladinmod.actions.ModifyDexterityAction;

public class PathOfPatiencePower extends AbstractPower
{
    public static final String POWER_ID = "PaladinMod:PathOfPatiencePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = PaladinMod.TEMP_POWER;
    private int drawAmt;

    public PathOfPatiencePower(AbstractCreature owner, int cardDraw, int dexAmount)
    {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.drawAmt = cardDraw;
        this.amount = dexAmount;
        this.updateDescription();
        this.img = new Texture(PaladinMod.makePath(IMG));
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount)
    {
        if (info.type != DamageInfo.DamageType.THORNS && info.owner != null && info.owner != this.owner && damageAmount > 0 )
        {
            this.flash();

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DrawCardNextTurnPower(this.owner, this.drawAmt), this.drawAmt));
            AbstractDungeon.actionManager.addToBottom(new ModifyDexterityAction(this.owner, this.amount));
        }

        return damageAmount;
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.drawAmt + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }
}
