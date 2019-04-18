package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;

public class CureWounds extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:CureWounds";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final String      UPGRADE_DESC      = cardStrings.UPGRADE_DESCRIPTION;
    private static final int         COST              = 1;
    private static final int         EXHAUST_AMT       = 1;
    private static final int         CARD_DRAW_AMT     = 1;
    private static final int         UPGRADE_DRAW_AMT  = 1;
    private static final CardType    TYPE              = CardType.SKILL;
    private static final CardRarity  RARITY            = CardRarity.UNCOMMON;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public CureWounds()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET);
        this.misc = EXHAUST_AMT;
        this.magicNumber = this.baseMagicNumber = CARD_DRAW_AMT;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new CureWounds();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_DRAW_AMT);
            this.rawDescription = UPGRADE_DESC;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new ExhaustAction(player, player, this.misc, false));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(player, this.magicNumber));
    }
}
