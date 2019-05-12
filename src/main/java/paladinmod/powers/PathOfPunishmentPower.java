package paladinmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import paladinmod.PaladinMod;

public class PathOfPunishmentPower extends AbstractPower
{
    public static final String POWER_ID = "PaladinMod:PathOfPunishmentPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = PaladinMod.TEMP_POWER;

    public PathOfPunishmentPower(AbstractCreature owner, int amount)
    {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture(PaladinMod.makePath(IMG));
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount)
    {
        if (info.type != DamageInfo.DamageType.THORNS && info.owner != null && info.owner != this.owner && damageAmount > 0 )
        {
            this.flash();

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthNextTurnPower(this.owner, this.amount), this.amount));
        }

        return damageAmount;
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
