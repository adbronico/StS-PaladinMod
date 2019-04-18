package paladinmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import paladinmod.PaladinMod;

public class AuraOfPurityPower extends AbstractPower
{
    public static final String POWER_ID = "PaladinMod:AuraOfPurityPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = "powers/Divinity.png";

    public AuraOfPurityPower(AbstractCreature owner)
    {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.description = DESCRIPTIONS[0];
        this.img = new Texture(PaladinMod.makePath(IMG));
    }
}
