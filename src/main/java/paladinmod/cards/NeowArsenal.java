package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.powers.NeowArsenalPower;

public class NeowArsenal extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:NeowArsenal";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 2;
    private static final int         UPGRADED_COST     = 1;
    private static final int         SMITE_ADD_AMT     = 1;
    private static final CardType    TYPE              = CardType.POWER;
    private static final CardRarity  RARITY            = CardRarity.UNCOMMON;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public NeowArsenal()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.magicNumber = this.baseMagicNumber = SMITE_ADD_AMT;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new NeowArsenal();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADED_COST);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new NeowArsenalPower(player, this.magicNumber), this.magicNumber));
    }
}
