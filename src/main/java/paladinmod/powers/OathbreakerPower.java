package paladinmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import paladinmod.PaladinMod;

public class OathbreakerPower extends AbstractPower
{
    public  static final String       POWER_ID     = "PaladinMod:OathbreakerPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public  static final String       NAME         = powerStrings.NAME;
    public  static final String[]     DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public  static final String       IMAGE        = PaladinMod.TEMP_POWER;

    public OathbreakerPower(AbstractCreature owner)
    {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.description = DESCRIPTIONS[0];
        this.img = new Texture(PaladinMod.makePath(IMAGE));
    }
}
