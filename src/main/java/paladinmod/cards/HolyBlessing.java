package paladinmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.actions.FactorDivinityAction;

public class HolyBlessing extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:HolyBlessing";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final String      UPGRADE_DESC      = cardStrings.UPGRADE_DESCRIPTION;
    private static final int         COST              = 1;
    private static final float       DIV_MULT          = 2.0f;
    private static final float       UPGRADE_MULT      = 3.0f;
    private static final CardType    TYPE              = CardType.SKILL;
    private static final CardRarity  RARITY            = CardRarity.RARE;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    private float divMultiplier;

    public HolyBlessing()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET);
        this.divMultiplier = DIV_MULT;
        this.exhaust = true;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new HolyBlessing();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESC;
            this.divMultiplier = UPGRADE_MULT;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new FactorDivinityAction(this.divMultiplier));
    }
}
