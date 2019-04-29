package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import paladinmod.PaladinMod;
import paladinmod.actions.LoseDivinityAction;
import paladinmod.powers.OriginalSinPower;

public class OriginalSin extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:OriginalSin";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      IMAGE             = "cards/OriginalSin";
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 0;
    private static final int         DIV_LOSS_AMT      = 10;
    private static final int         DEBUFF_AMT        = 3;
    private static final int         DEBUFF_AMT_LOSS   = -1;
    private static final int         ENERGY_GAIN       = 1;
    private static final CardType    TYPE              = CardType.POWER;
    private static final CardRarity  RARITY            = CardRarity.RARE;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public OriginalSin()
    {
        super(ID, NAME, PaladinMod.makePath(IMAGE), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.divinity = this.baseDivinity = DIV_LOSS_AMT;
        this.magicNumber = this.baseMagicNumber = DEBUFF_AMT;
        this.isInnate = true;
        this.isEthereal = true;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new OriginalSin();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeMagicNumber(DEBUFF_AMT_LOSS);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new LoseDivinityAction(this.divinity));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new FrailPower(player, this.magicNumber, false), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new VulnerablePower(player, this.magicNumber, false), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new OriginalSinPower(player, ENERGY_GAIN), ENERGY_GAIN));
    }
}
