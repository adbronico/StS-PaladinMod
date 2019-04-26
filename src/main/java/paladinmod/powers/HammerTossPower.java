package paladinmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EntanglePower;
import paladinmod.PaladinMod;

public class HammerTossPower extends AbstractPower
{
    public static final String POWER_ID = "PaladinMod:HammerTossPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = PaladinMod.TEMP_POWER;

    public HammerTossPower(AbstractCreature owner)
    {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = 0;
        this.description = DESCRIPTIONS[0];
        this.img = new Texture(PaladinMod.makePath(IMG));
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
    }

    @Override
    public void atStartOfTurn()
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new EntanglePower(this.owner)));
        AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
}
