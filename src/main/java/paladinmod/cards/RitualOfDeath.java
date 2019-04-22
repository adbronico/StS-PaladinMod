package paladinmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.actions.DefeatEnemyAction;
import paladinmod.actions.RitualAction;

public class RitualOfDeath extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:RitualOfDeath";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      IMAGE             = "cards/RitualOfDeath";
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final String[]    EXT_DESC          = cardStrings.EXTENDED_DESCRIPTION;
    private static final int         COST              = 2;
    private static final int         RITUAL_COUNT      = 15;
    private static final int         RITUAL_DISCOUNT   = -5;
    private static final CardType    TYPE              = CardType.SKILL;
    private static final CardRarity  RARITY            = CardRarity.RARE;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public RitualOfDeath()
    {
        super(ID, NAME, PaladinMod.makePath(IMAGE), COST, DESCRIPTION, TYPE, RARITY, TARGET, true);
        this.magicNumber = this.baseMagicNumber = RITUAL_COUNT;
    }

    @Override
    public void initializeDescription()
    {
        if(this.baseMagicNumber == 0)
        {
            this.rawDescription = EXT_DESC[0];
            this.exhaust = true;
        }
        super.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new RitualOfDeath();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeMagicNumber(RITUAL_DISCOUNT);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        if(this.baseMagicNumber > 0)
        {
            AbstractDungeon.actionManager.addToBottom(new RitualAction(this.uuid));
        }
        else
        {
            for(AbstractMonster herman : AbstractDungeon.getCurrRoom().monsters.monsters)
            {
                AbstractDungeon.actionManager.addToBottom(new DefeatEnemyAction(herman));
            }
        }
    }
}
