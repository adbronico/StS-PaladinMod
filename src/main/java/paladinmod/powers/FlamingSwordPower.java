package paladinmod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.FlameBarrierPower;

public class FlamingSwordPower extends FlameBarrierPower
{
    public static final String POWER_ID = "PaladinMod:FlamingSwordPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FlamingSwordPower(AbstractCreature owner, int thornsDamage)
    {
        super(owner, thornsDamage);

        this.ID = POWER_ID;
        this.name = NAME;
    }

    @Override
    public void atStartOfTurn()
    {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, POWER_ID));
    }
}
