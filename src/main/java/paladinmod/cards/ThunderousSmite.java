package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.patches.PaladinTags;

public class ThunderousSmite extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:ThunderousSmite";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      IMAGE             = "cards/ThunderousSmite";
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 1;
    private static final int         DMG_AMT           = 7;
    private static final int         UPGRADE_DMG_ADD   = 1;
    private static final int         FIRST_CARD_AMT    = 5;
    private static final int         FIRST_CARD_ADD    = 1;
    private static final CardType    TYPE              = CardType.ATTACK;
    private static final CardRarity  RARITY            = CardRarity.COMMON;
    private static final CardTarget  TARGET            = CardTarget.ENEMY;

    public ThunderousSmite()
    {
        super(ID, NAME, PaladinMod.makePath(IMAGE), COST, DESCRIPTION, TYPE, RARITY, TARGET, true);
        this.baseDamage = DMG_AMT;
        this.magicNumber = this.baseMagicNumber = FIRST_CARD_AMT;
        this.tags.add(PaladinTags.SMITE_TAG);
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new ThunderousSmite();
    }

    @Override
    public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster monster, float tmp)
    {
        int bonusDamage = 0;

        if(AbstractDungeon.player.cardsPlayedThisTurn <= 1)
        {
            bonusDamage = this.magicNumber;
        }

        return tmp + bonusDamage;
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DMG_ADD);
            this.upgradeMagicNumber(FIRST_CARD_ADD);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(player, this.damage, this.damageTypeForTurn)));
    }
}
