package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.powers.WayOfVengeancePower;

public class WayOfVengeance extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:WayOfVengeance";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 2;
    private static final int         RAND_DMG_AMT      = 3;
    private static final int         UPGRADE_DMG_ADD   = 2;
    private static final CardType    TYPE              = CardType.POWER;
    private static final CardRarity  RARITY            = CardRarity.RARE;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public WayOfVengeance()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.magicNumber = this.baseMagicNumber = RAND_DMG_AMT;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new WayOfVengeance();
    }

    @Override
    public void upgrade()
    {
        if(!upgraded)
        {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_DMG_ADD);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new WayOfVengeancePower(player, this.magicNumber), this.magicNumber));
    }
}
