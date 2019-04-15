package paladinmod.characters;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import paladinmod.PaladinMod;
import paladinmod.patches.AbstractCardEnum;
import paladinmod.patches.ThePaladinEnum;

import java.util.ArrayList;

public class ThePaladin extends CustomPlayer
{
    private static final int ENERGY_PER_TURN = 3;
    // TODO: create these
    private static final String MY_CHARACTER_SKELETON_ATLAS = "img/char/skeleton.atlas"; // spine animation atlas
    private static final String MY_CHARACTER_SKELETON_JSON  = "img/char/skeleton.json"; // spine animation json

    private static final String CHARACTER_NAME = "The Paladin";
    private static final Color CHARACTER_COLOR = Color.GOLD;
    //TODO: Write these
    private static final String CHARACTER_DESCRIPTOR = "Paladin TBD";
    private static final String CHARACTER_HEART_TEXT = "Paladin Heart TBD";
    private static final String CHARACTER_VAMP_TEXT  = "Vampire Paladin TBD";

    private static final int STARTING_HP   = 60;
    private static final int MAX_HP        = 60;
    private static final int MAX_ORBS      = 0;
    private static final int STARTING_GOLD = 90;
    private static final int HAND_SIZE     = 5;

    private static final int ASCENSION_HP_LOSS = 5;
    private static final int HEART_ATTACK_SLASHES = 3;

    // TODO: create these orb layer images
    private static final String[] orbTextures  =
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
        super(name, ThePaladinEnum.THE_PALADIN, orbTextures,
                PaladinMod.makePath("char/orb/vfx.png"), new SpriterAnimation(PaladinMod.makePath("char/animation.scml")));

        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 220.0F * Settings.scale);

        initializeClass(null, PaladinMod.makePath(PaladinMod.PALADIN_SHOULDER_2),
                        PaladinMod.makePath(PaladinMod.PALADIN_SHOULDER_1),
                        PaladinMod.makePath(PaladinMod.PALADIN_CORPSE),
                        getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        loadAnimation(MY_CHARACTER_SKELETON_ATLAS, MY_CHARACTER_SKELETON_JSON, 1.0F);

        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());
    }

    @Override
    public ArrayList<String> getStartingDeck()
    {
        ArrayList<String> startingDeck = new ArrayList<>();
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
        return startingDeck;
    }

    @Override
    public ArrayList<String> getStartingRelics()
    {
        ArrayList<String> startingRelics = new ArrayList<>();
        // TODO: design starting relic(s)
        return startingRelics;
    }

    @Override
    public CharSelectInfo getLoadout()
    {
        return new CharSelectInfo(CHARACTER_NAME, CHARACTER_DESCRIPTOR,
                STARTING_HP, MAX_HP, MAX_ORBS, STARTING_GOLD, HAND_SIZE,
                this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass)
    {
        return CHARACTER_NAME;
    }

    @Override
    public AbstractCard.CardColor getCardColor()
    {
        return AbstractCardEnum.PAL_GOLD;
    }

    @Override
    public Color getCardRenderColor()
    {
        return CHARACTER_COLOR;
    }

    @Override
    public AbstractCard getStartCardForEvent()
    {
        // TODO: starter card that will show up in the Gremlin match-game. Decide which to put here
        return null;
    }

    @Override
    public Color getCardTrailColor()
    {
        return CHARACTER_COLOR;
    }

    @Override
    public int getAscensionMaxHPLoss()
    {
        return ASCENSION_HP_LOSS;
    }

    @Override
    public BitmapFont getEnergyNumFont()
    {
        // TODO: figure this out
        return null;
    }

    @Override
    public void doCharSelectScreenSelectEffect()
    {
        // TODO: selection effects. See 5.0 page or Seeker mod for ideas
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey()
    {
        // TODO: see 5.0 page
        return null;
    }

    @Override
    public String getLocalizedCharacterName()
    {
        return CHARACTER_NAME;
    }

    @Override
    public AbstractPlayer newInstance()
    {
        return new ThePaladin(this.name);
    }

    @Override
    public String getSpireHeartText()
    {
        return CHARACTER_HEART_TEXT;
    }

    @Override
    public Color getSlashAttackColor()
    {
        return CHARACTER_COLOR;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect()
    {
        return new AbstractGameAction.AttackEffect[HEART_ATTACK_SLASHES];
    }

    @Override
    public String getVampireText()
    {
        // TODO: write Vampire text
        return CHARACTER_VAMP_TEXT;
    }
}
