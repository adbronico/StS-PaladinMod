package paladinmod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import paladinmod.PaladinMod;

public class BagOfHolding extends CustomRelic
{
    public static final String ID = "PaladinMod:BagOfHolding";
    public static final int RETAIN_AMT = 2;

    public BagOfHolding()
    {
        super(ID, new Texture(PaladinMod.makePath("relics/default")), new Texture(PaladinMod.makePath("relics/outline/default")), RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription()
    {
        return this.DESCRIPTIONS[0] + RETAIN_AMT + this.DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy()
    {
        return new BagOfHolding();
    }

    @Override
    public void onPlayerEndTurn()
    {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new RetainCardsAction(AbstractDungeon.player, RETAIN_AMT));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }
}
