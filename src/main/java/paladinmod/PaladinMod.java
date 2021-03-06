package paladinmod;

import basemod.BaseMod;
import basemod.abstracts.CustomCard;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import paladinmod.cards.*;
import paladinmod.characters.ThePaladin;
import paladinmod.dynvar.CardDrawVariable;
import paladinmod.dynvar.DivinityVariable;
import paladinmod.dynvar.RitualVariable;
import paladinmod.patches.AbstractCardEnum;
import paladinmod.patches.PaladinTags;
import paladinmod.patches.ThePaladinEnum;
import paladinmod.powers.AuraOfCouragePower;
import paladinmod.powers.AuraOfPurityPower;
import paladinmod.relics.BagOfHolding;
import paladinmod.relics.TowerShield;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpireInitializer
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

    private static final String IMG_FOLDER = "palresources/img/";

    public static final String TEMP_POWER = "powers/Divinity.png";

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

    public enum PositionEnum
    {
        TOP,
        BOTTOM,
        RANDOM
    }

    public PaladinMod()
    {
        BaseMod.subscribe(this);

        logger.info("Creating color");
        BaseMod.addColor(AbstractCardEnum.PAL_GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD,
                makePath(ATTACK_GOLD), makePath(SKILL_GOLD), makePath(POWER_GOLD), makePath(ENERGY_ORB_GOLD),
                makePath(ATTACK_GOLD_PORTRAIT), makePath(SKILL_GOLD_PORTRAIT), makePath(POWER_GOLD_PORTRAIT),
                makePath((ENERGY_ORB_GOLD_PORTRAIT)), makePath(CARD_ENERGY_ORB_GOLD));
        logger.info("Color created");
    }

    public static void initialize()
    {
        new PaladinMod();
    }

    @Override
    public void receiveEditCards()
    {
        logger.info("Adding DivinityVariable");

        BaseMod.addDynamicVariable(new CardDrawVariable());
        BaseMod.addDynamicVariable(new DivinityVariable());
        BaseMod.addDynamicVariable(new RitualVariable());

        logger.info("Adding Paladin class cards");

        List<CustomCard> paladinCards = new ArrayList<>();
        paladinCards.add(new AngelForm());
        paladinCards.add(new AuraOfCourage());
        paladinCards.add(new AuraOfHate());
        paladinCards.add(new AuraOfPurity());
        paladinCards.add(new AuraOfVitality());
        paladinCards.add(new Blight());
        paladinCards.add(new BlindingSmite());
        paladinCards.add(new BrandingSmite());
        paladinCards.add(new CleansingTouch());
        paladinCards.add(new ChannelDivinity());
        paladinCards.add(new ChooseYourPath());
        paladinCards.add(new Condemn());
        paladinCards.add(new CrownOfMadness());
        paladinCards.add(new CureWounds());
        paladinCards.add(new Darkness());
        paladinCards.add(new DefensiveStance());
        paladinCards.add(new DestructiveWave());
        paladinCards.add(new DisarmingStrike());
        paladinCards.add(new DivineFavor());
        paladinCards.add(new DivineJustice());
        paladinCards.add(new DreadLord());
        paladinCards.add(new DreadfulAspect());
        paladinCards.add(new FlamingSword());
        paladinCards.add(new FocusedSmite());
        paladinCards.add(new FullPlate());
        paladinCards.add(new GuidedStrike());
        paladinCards.add(new GuidingHand());
        paladinCards.add(new HammerToss());
        paladinCards.add(new HellishRebuke());
        paladinCards.add(new HolyBlessing());
        paladinCards.add(new HolyShield());
        paladinCards.add(new HolyWrath());
        paladinCards.add(new InflictWounds());
        paladinCards.add(new LayOnHands());
        paladinCards.add(new MarkOfVengeance());
        paladinCards.add(new Massacre());
        paladinCards.add(new Meditate());
        paladinCards.add(new NeowArsenal());
        paladinCards.add(new NeowBlessing());
        paladinCards.add(new NeowGuidance());
        paladinCards.add(new NeowRage());
        paladinCards.add(new Oathbreaker());
        paladinCards.add(new OriginalSin());
        paladinCards.add(new PathOfEquilibrium());
        paladinCards.add(new PathOfPatience());
        paladinCards.add(new PathOfPunishment());
        paladinCards.add(new PerfectedSmite());
        paladinCards.add(new Prayer());
        paladinCards.add(new Protection());
        paladinCards.add(new Purge());
        paladinCards.add(new PurgingStrike());
        paladinCards.add(new Recover());
        paladinCards.add(new Redemption());
        paladinCards.add(new RelentlessAssault());
        paladinCards.add(new RitualOfDeath());
        paladinCards.add(new RitualOfLife());
        paladinCards.add(new RitualOfProtection());
        paladinCards.add(new SearingSmite());
        paladinCards.add(new Shield());
        paladinCards.add(new ShieldBash());
        paladinCards.add(new ShieldOfFaith());
        paladinCards.add(new SiphoningStrike());
        paladinCards.add(new Smite());
        paladinCards.add(new SolemnVigil());
        paladinCards.add(new StaggeringSmite());
        paladinCards.add(new SwordAndShield());
        paladinCards.add(new ThunderousSmite());
        paladinCards.add(new VorpalBlade());
        paladinCards.add(new WardedStrike());
        paladinCards.add(new WayOfProtection());
        paladinCards.add(new WayOfTheAncients());
        paladinCards.add(new WayOfVengeance());
        paladinCards.add(new WrathfulSmite());

        // Unlock all cards from first run
        for(CustomCard card : paladinCards)
        {
            BaseMod.addCard(card);
            UnlockTracker.unlockCard(card.cardID);
        }
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
        // TODO: make these language-specific - see SlimeBound mod
        // TODO: Update description
        logger.info("Adding custom keywords");

        Gson gson = new Gson();
        String json = Gdx.files.internal(("palresources/localization/" + "eng" + "/Paladin-KeywordStrings.json")).readString(String.valueOf(StandardCharsets.UTF_8));

        final com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords =
                (com.evacipated.cardcrawl.mod.stslib.Keyword[])gson.fromJson(json, (Class) com.evacipated.cardcrawl.mod.stslib.Keyword[].class);
        for (com.evacipated.cardcrawl.mod.stslib.Keyword kw : keywords) {

            BaseMod.addKeyword(kw.PROPER_NAME, kw.NAMES, kw.DESCRIPTION);
        }
        logger.info("done editing keywords");
    }

    @Override
    public void receiveEditRelics()
    {
        BaseMod.addRelicToCustomPool(new TowerShield(), AbstractCardEnum.PAL_GOLD);
        BaseMod.addRelicToCustomPool(new BagOfHolding(), AbstractCardEnum.PAL_GOLD);
    }

    @Override
    public void receiveEditStrings()
    {
        // TODO: replace this hard-coded language with language-specifics
        logger.info("Loading Strings");

        String cardStrings = Gdx.files.internal("palresources/localization/" + "eng" + "/Paladin-CardStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);

        String powerStrings = Gdx.files.internal("palresources/localization/" + "eng" + "/Paladin-PowerStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);

        String relicStrings = Gdx.files.internal("palresources/localization/" + "eng" + "/Paladin-RelicStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);

        String uiStrings = Gdx.files.internal("palresources/localization/" + "eng" + "/Paladin-UIStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(UIStrings.class, uiStrings);
    }

    @Override
    public void receiveCardUsed(AbstractCard card)
    {
        AbstractPlayer player = AbstractDungeon.player;

        if(card.tags.contains(PaladinTags.SMITE_TAG))
        {
            for(AbstractCard handCard : player.hand.group)
            {
                if(handCard.cardID.equals(FocusedSmite.ID))
                {
                    handCard.updateCost(-1);
                }
            }

            for(AbstractCard deckCard : player.drawPile.group)
            {
                if(deckCard.cardID.equals(FocusedSmite.ID))
                {
                    deckCard.updateCost(-1);
                }
            }

            for(AbstractCard disCard : player.discardPile.group)
            {
                if(disCard.cardID.equals(FocusedSmite.ID))
                {
                    disCard.updateCost(-1);
                }
            }
        }
    }

    @Override
    public void receivePowersModified()
    {
        AbstractPlayer player = AbstractDungeon.player;

        if(player.hasPower(AuraOfCouragePower.POWER_ID) && player.hasPower(WeakPower.POWER_ID))
        {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(player, player, WeakPower.POWER_ID));
            player.getPower(AuraOfCouragePower.POWER_ID).flash();
        }
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom)
    {

    }

    @Override
    public void receivePostDraw(AbstractCard card)
    {
        AbstractPlayer player = AbstractDungeon.player;

        if(player.hasPower(AuraOfPurityPower.POWER_ID) && (AbstractCard.CardType.STATUS.equals(card.type) || AbstractCard.CardType.CURSE.equals(card.type)))
        {
            AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, player.hand));
        }
    }

    @Override
    public void receivePostDungeonInitialize()
    {

    }

    @Override
    public void receivePostExhaust(AbstractCard abstractCard)
    {

    }

    public static boolean isMonsterAttacking(AbstractMonster monster)
    {
        if(monster != null && (AbstractMonster.Intent.ATTACK == monster.intent|| AbstractMonster.Intent.ATTACK_BUFF == monster.intent
                || AbstractMonster.Intent.ATTACK_DEBUFF == monster.intent || AbstractMonster.Intent.ATTACK_DEFEND == monster.intent))
        {
            return true;
        }
        return false;
    }

    public static AbstractCard returnRandomCardByTag(AbstractCard.CardTags tag)
    {
        CardGroup cardPool = AbstractDungeon.srcCommonCardPool;
        ArrayList<AbstractCard> cardList = new ArrayList();

        for(AbstractCard card : cardPool.group)
        {
            if(card.tags.contains(tag))
            {
                cardList.add(card);
            }
        }

        cardPool = AbstractDungeon.uncommonCardPool;

        for(AbstractCard card : cardPool.group)
        {
            if(card.tags.contains(tag))
            {
                cardList.add(card);
            }
        }

        cardPool = AbstractDungeon.rareCardPool;

        for(AbstractCard card : cardPool.group)
        {
            if(card.tags.contains(tag))
            {
                cardList.add(card);
            }
        }

        return cardList.get(AbstractDungeon.cardRandomRng.random(cardList.size() - 1));
    }

    public static String makePath(String fileName)
    {
        String path = IMG_FOLDER + fileName;

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

    public static int handleAmount(int amount)
    {
        if(amount >= 999)
        {
            return 999;
        }
        else if(amount <= -999)
        {
            return -999;
        }
        else
        {
            return amount;
        }
    }
}
