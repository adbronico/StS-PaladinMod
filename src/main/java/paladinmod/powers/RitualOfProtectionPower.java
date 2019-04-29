package paladinmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import paladinmod.PaladinMod;
import paladinmod.actions.ModifyDexterityAction;

public class RitualOfProtectionPower extends AbstractPower
{
    public static final String POWER_ID = "PaladinMod:RitualOfProtectionPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = PaladinMod.TEMP_POWER;

    public RitualOfProtectionPower(AbstractCreature owner, int amount)
    {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = PaladinMod.handleAmount(amount);
        this.updateDescription();
        this.img = new Texture(PaladinMod.makePath(IMG));
    }

    @Override
    public void atStartOfTurn()
    {
        AbstractDungeon.actionManager.addToBottom(new ModifyDexterityAction(this.owner, this.amount));
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
