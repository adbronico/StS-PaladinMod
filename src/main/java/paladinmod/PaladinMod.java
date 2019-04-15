package paladinmod;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import paladinmod.characters.ThePaladin;
import paladinmod.patches.AbstractCardEnum;
import paladinmod.patches.ThePaladinEnum;

public class PaladinMod implements
        EditCharactersSubscriber,
        EditRelicsSubscriber,
        EditCardsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        OnCardUseSubscriber,
        OnPowersModifiedSubscriber,
        PostBattleSubscriber,
        PostDungeonInitializeSubscriber,
        PostExhaustSubscriber,
        PostDrawSubscriber
{
    private static final Logger logger = LogManager.getLogger(PaladinMod.class.getName());

    private static final String ASSET_FOLDER = "img";

    // TODO: create these assets

    // Card Backgrounds
    private static final String ATTACK_GOLD = "512/bg_attack_gold.png";
    private static final String SKILL_GOLD = "512/bg_skill_gold.png";
    private static final String POWER_GOLD = "512/bg_power_gold.png";
    private static final String ENERGY_ORB_GOLD = "512/card_gold_orb.png";
    private static final String CARD_ENERGY_ORB_GOLD = "512/card_gold_small_orb.png";

    private static final String ATTACK_GOLD_PORTRAIT = "1024/bg_attack_gold.png";
    private static final String SKILL_GOLD_PORTRAIT = "1024/bg_skill_gold.png";
    private static final String POWER_GOLD_PORTRAIT = "1024/bg_power_gold.png";
    private static final String ENERGY_ORB_GOLD_PORTRAIT = "1024/card_gold_orb.png";

    // Character Assets
    private static final String PALADIN_BUTTON     = "charSelect/button.png";
    private static final String PALADIN_PORTRAIT   = "charSelect/portrait.jpg";
    public static final String PALADIN_SHOULDER_1 = "char/shoulder.png";
    public static final String PALADIN_SHOULDER_2 = "char/shoulder2.png";
    public static final String PALADIN_CORPSE     = "char/corpse.png";

    public PaladinMod()
    {
        logger.info("Creating color");
        BaseMod.addColor(AbstractCardEnum.PAL_GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD,
                makePath(ATTACK_GOLD), makePath(SKILL_GOLD), makePath(POWER_GOLD), makePath(ENERGY_ORB_GOLD),
                makePath(ATTACK_GOLD_PORTRAIT), makePath(SKILL_GOLD_PORTRAIT), makePath(POWER_GOLD_PORTRAIT),
                makePath((ENERGY_ORB_GOLD_PORTRAIT)), makePath(CARD_ENERGY_ORB_GOLD));
    }

    @Override
    public void receiveEditCards()
    {

    }

    @Override
    public void receiveEditCharacters()
    {
        logger.info("Begin editing characters.");

        logger.info("Adding " + ThePaladinEnum.THE_PALADIN.toString());
        BaseMod.addCharacter(new ThePaladin(CardCrawlGame.playerName),
                makePath(PALADIN_BUTTON), makePath(PALADIN_PORTRAIT), ThePaladinEnum.THE_PALADIN);

        logger.info("Done editing characters.");
    }

    @Override
    public void receiveEditKeywords()
    {

    }

    @Override
    public void receiveEditRelics()
    {

    }

    @Override
    public void receiveEditStrings()
    {

    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard)
    {

    }

    @Override
    public void receivePowersModified()
    {

    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom)
    {

    }

    @Override
    public void receivePostDraw(AbstractCard abstractCard)
    {

    }

    @Override
    public void receivePostDungeonInitialize()
    {

    }

    @Override
    public void receivePostExhaust(AbstractCard abstractCard)
    {

    }

    public static String makePath(String fileName)
    {
        String path = ASSET_FOLDER + fileName;

        if(!hasExtension(fileName))
        {
            path += ".png";
        }

        return path;
    }

    public static boolean hasExtension(String fileName)
    {
        return fileName.lastIndexOf('.') > 0;
    }
}
