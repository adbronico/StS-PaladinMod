package paladinmod.characters;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbRed;
import paladinmod.PaladinMod;
import paladinmod.patches.ThePaladinEnum;

import java.util.ArrayList;

public class ThePaladin extends CustomPlayer
{
    public static final int ENERGY_PER_TURN = 3;

    // TODO: create these orb layer images
    public static final String[] orbTextures  =
    {
            PaladinMod.makePath("char/orb/layer1.png"),
            PaladinMod.makePath("char/orb/layer2.png"),
            PaladinMod.makePath("char/orb/layer3.png"),
            PaladinMod.makePath("char/orb/layer4.png"),
            PaladinMod.makePath("char/orb/layer5.png"),
            PaladinMod.makePath("char/orb/layer6.png"),
            PaladinMod.makePath("char/orb/layer1d.png"),
            PaladinMod.makePath("char/orb/layer2d.png"),
            PaladinMod.makePath("char/orb/layer3d.png"),
            PaladinMod.makePath("char/orb/layer4d.png"),
            PaladinMod.makePath("char/orb/layer5d.png")
    };

    public ThePaladin(String name)
    {
        // TODO: create vfx.png and animation.scml
        super(name, ThePaladinEnum.THE_PALADIN, orbTextures, PaladinMod.makePath("char/orb/vfx.png"), new SpriterAnimation(PaladinMod.makePath("char/animation.scml")));
    }

    @Override
    public ArrayList<String> getStartingDeck()
    {
        /* TODO: implement starter cards
         * Defend
         * Defend
         * Defend
         * Defend
         * Smite
         * Smite
         * Smite
         * Smite
         * Lay on Hands
         * Holy Wrath
         */
        return null;
    }

    @Override
    public ArrayList<String> getStartingRelics()
    {
        return null;
    }

    @Override
    public CharSelectInfo getLoadout()
    {
        return null;
    }

    @Override
    public String getTitle(PlayerClass playerClass)
    {
        return null;
    }

    @Override
    public AbstractCard.CardColor getCardColor()
    {
        return null;
    }

    @Override
    public Color getCardRenderColor()
    {
        return null;
    }

    @Override
    public AbstractCard getStartCardForEvent()
    {
        return null;
    }

    @Override
    public Color getCardTrailColor()
    {
        return null;
    }

    @Override
    public int getAscensionMaxHPLoss()
    {
        return 0;
    }

    @Override
    public BitmapFont getEnergyNumFont()
    {
        return null;
    }

    @Override
    public void doCharSelectScreenSelectEffect()
    {

    }

    @Override
    public String getCustomModeCharacterButtonSoundKey()
    {
        return null;
    }

    @Override
    public String getLocalizedCharacterName()
    {
        return null;
    }

    @Override
    public AbstractPlayer newInstance()
    {
        return null;
    }

    @Override
    public String getSpireHeartText()
    {
        return null;
    }

    @Override
    public Color getSlashAttackColor()
    {
        return null;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect()
    {
        return new AbstractGameAction.AttackEffect[0];
    }

    @Override
    public String getVampireText()
    {
        return null;
    }
}
