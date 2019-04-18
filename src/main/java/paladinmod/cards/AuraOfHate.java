package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.powers.AuraOfHatePower;

public class AuraOfHate extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:AuraOfHate";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 2;
    private static final int         BUFF_AMT          = 2;
    private static final int         UPGRADE_BUFF_ADD  = 1;
    private static final CardType    TYPE              = CardType.POWER;
    private static final CardRarity  RARITY            = CardRarity.UNCOMMON;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public AuraOfHate()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = BUFF_AMT;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new AuraOfHate();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_BUFF_ADD);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new AuraOfHatePower(player, this.magicNumber), this.magicNumber));
    }
}
