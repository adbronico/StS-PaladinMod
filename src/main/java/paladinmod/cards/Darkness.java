package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import paladinmod.PaladinMod;

public class Darkness extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:Darkness";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final String      UPGRADE_DESC      = cardStrings.UPGRADE_DESCRIPTION;
    private static final int         COST              = 2;
    private static final int         INTANGIBLE_AMT    = 1;
    private static final CardType    TYPE              = CardType.SKILL;
    private static final CardRarity  RARITY            = CardRarity.UNCOMMON;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public Darkness()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.magicNumber = this.baseMagicNumber = INTANGIBLE_AMT;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new Darkness();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESC;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new IntangiblePlayerPower(player, this.magicNumber), this.magicNumber));
        if(upgraded)
        {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(player, this.magicNumber));
        }
    }
}
