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

public class PerfectedSmite extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:PerfectedSmite";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      IMAGE             = "cards/PerfectedSmite";
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 2;
    private static final int         DMG_AMT           = 6;
    private static final int         BONUS_AMT         = 2;
    private static final int         UPGRADE_BONUS_ADD = 1;
    private static final CardType    TYPE              = CardType.ATTACK;
    private static final CardRarity  RARITY            = CardRarity.COMMON;
    private static final CardTarget  TARGET            = CardTarget.ENEMY;

    public PerfectedSmite()
    {
        super(ID, NAME, PaladinMod.makePath(IMAGE), COST, DESCRIPTION, TYPE, RARITY, TARGET, true);
        this.baseDamage = DMG_AMT;
        this.magicNumber = this.baseMagicNumber = BONUS_AMT;
        this.tags.add(PaladinTags.SMITE_TAG);
    }

    @Override
    public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster monster, float tmp)
    {
        int smiteCount = 0;

        for(AbstractCard card : AbstractDungeon.player.hand.group)
        {
            if(card.hasTag(PaladinTags.SMITE_TAG))
            {
                smiteCount++;
            }
        }

        for(AbstractCard card : AbstractDungeon.player.drawPile.group)
        {
            if(card.hasTag(PaladinTags.SMITE_TAG))
            {
                smiteCount++;
            }
        }

        for(AbstractCard card : AbstractDungeon.player.discardPile.group)
        {
            if(card.hasTag(PaladinTags.SMITE_TAG))
            {
                smiteCount++;
            }
        }

        return tmp + (smiteCount * magicNumber);
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new PerfectedSmite();
    }

    @Override
    public void upgrade()
    {
        if (!this.upgraded)
        {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_BONUS_ADD);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(player, this.damage, this.damageTypeForTurn)));
    }
}
