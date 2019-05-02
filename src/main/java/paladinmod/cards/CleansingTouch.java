package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.actions.RemoveRandomDebuffAction;

public class CleansingTouch extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:CleansingTouch";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      IMAGE             = "cards/CleansingTouch";
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final String      UPGRADE_DESC      = cardStrings.UPGRADE_DESCRIPTION;
    private static final int         COST              = 1;
    private static final int         UPGRADED_COST     = 0;
    private static final int         DEBUFF_REMOVE_AMT = 1;
    private static final int         CARD_DRAW_AMT     = 1;
    private static final CardType    TYPE              = CardType.SKILL;
    private static final CardRarity  RARITY            = CardRarity.COMMON;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public CleansingTouch()
    {
        super(ID, NAME, PaladinMod.makePath(IMAGE), COST, DESCRIPTION, TYPE, RARITY, TARGET, true);
        this.magicNumber = this.baseMagicNumber = DEBUFF_REMOVE_AMT;
        this.cardDraw = this.baseCardDraw = CARD_DRAW_AMT;
        this.exhaust = true;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new CleansingTouch();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESC;
            this.upgradeBaseCost(UPGRADED_COST);
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new RemoveRandomDebuffAction(player));

        if(upgraded)
        {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(player, this.cardDraw));
        }
    }
}
