package paladinmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import paladinmod.PaladinMod;

public class WayOfProtectionPower extends AbstractPower
{
    public static final String POWER_ID = "PaladinMod:WayOfProtectionPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = PaladinMod.TEMP_POWER;

    public WayOfProtectionPower(AbstractCreature owner, int amount)
    {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = PaladinMod.handleAmount(amount);
        this.updateDescription();
        this.img = new Texture(PaladinMod.makePath(IMG));
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source)
    {
        if(power.ID.equals(DivinityPower.POWER_ID) && power.amount > 0)
        {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(target, target, this.amount));
        }
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
