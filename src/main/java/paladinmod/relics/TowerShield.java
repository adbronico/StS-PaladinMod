package paladinmod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import paladinmod.PaladinMod;

public class TowerShield extends CustomRelic
{
    public  static final String      ID                = "PaladinMod:TowerShield";
    public  static final int         BLOCK_AMT         = 3;

    public TowerShield()
    {
        super(ID, new Texture(PaladinMod.makePath("relics/default")), new Texture(PaladinMod.makePath("relics/outline/default")), RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription()
    {
        return this.DESCRIPTIONS[0] + BLOCK_AMT + this.DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy()
    {
        return new TowerShield();
    }

    @Override
    public void onPlayerEndTurn()
    {
        this.flash();
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, BLOCK_AMT));
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }
}
