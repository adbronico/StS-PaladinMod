package paladinmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import paladinmod.PaladinMod;
import paladinmod.patches.PaladinTags;

public class NeowArsenalPower extends AbstractPower
{
    public  static final String       POWER_ID     = "PaladinMod:NeowArsenalPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public  static final String       NAME         = powerStrings.NAME;
    public  static final String[]     DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public  static final String       IMAGE        = PaladinMod.TEMP_POWER;

    public NeowArsenalPower(AbstractCreature owner, int randomAmount)
    {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = randomAmount;
        this.updateDescription();
        this.img = new Texture(PaladinMod.makePath(IMAGE));
    }

    @Override
    public void atStartOfTurn()
    {
        for(int i = 0; i < this.amount; ++i)
        {
            AbstractCard card = PaladinMod.returnRandomCardByTag(PaladinTags.SMITE_TAG).makeCopy();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card));
        }

    }

    @Override
    public void updateDescription()
    {
        if (this.amount > 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }

    }
}
