package paladinmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import paladinmod.PaladinMod;

public class GuidingHandPower extends AbstractPower implements NonStackablePower
{
    public static final String POWER_ID = "PaladinMod:GuidingHandPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = "powers/Divinity.png";

    private AbstractCard reducedCard;

    public GuidingHandPower(AbstractCreature owner, AbstractCard card)
    {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.img = new Texture(PaladinMod.makePath(IMG));
        this.reducedCard = card;
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn()
    {
        this.reducedCard.setCostForTurn(this.reducedCard.cost - 1);
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.reducedCard.name + DESCRIPTIONS[1];
    }
}
