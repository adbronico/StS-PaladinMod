package paladinmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import paladinmod.PaladinMod;

public class OriginalSinPower extends AbstractPower
{
    public static final String POWER_ID = "PaladinMod:OriginalSinPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = "powers/Divinity.png";

    public OriginalSinPower(AbstractCreature owner, int amount)
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
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.amount));
        this.flash();
    }

    @Override
    public void updateDescription()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(DESCRIPTIONS[0]);

        for (int i = 0; i < this.amount; ++i)
        {
            //TODO: create yellow energy
            sb.append("[G] ");
        }

        sb.append(LocalizedStrings.PERIOD);
        this.description = sb.toString();
    }
}
