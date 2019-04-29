package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.actions.RitualAction;
import paladinmod.powers.RitualOfProtectionPower;

public class RitualOfProtection extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:RitualOfProtection";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      IMAGE             = "cards/RitualOfProtection";
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final String[]    EXT_DESC          = cardStrings.EXTENDED_DESCRIPTION;
    private static final int         COST              = 2;
    private static final int         UPGRADED_COST     = 1;
    private static final int         BLOCK_AMT         = 5;
    private static final int         RITUAL_COUNT      = 5;
    private static final int         DEX_GAIN_AMT      = 1;
    private static final CardType    TYPE              = CardType.SKILL;
    private static final CardRarity  RARITY            = CardRarity.RARE;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public RitualOfProtection()
    {
        super(ID, NAME, PaladinMod.makePath(IMAGE), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.block = this.baseBlock = BLOCK_AMT;
        this.magicNumber = this.baseMagicNumber = DEX_GAIN_AMT;
        this.ritual = this.baseRitual = RITUAL_COUNT;
        this.initializeDescription();
    }

    @Override
    public void initializeDescription()
    {
        if(this.baseRitual == 0)
        {
            this.rawDescription = EXT_DESC[0];
            this.exhaust = true;
        }
        else
        {
            this.rawDescription = DESCRIPTION;
            this.exhaust = false;
        }
        super.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new RitualOfProtection();
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
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, this.block));

        if(this.baseRitual > 0)
        {
            AbstractDungeon.actionManager.addToBottom(new RitualAction(this.uuid));
        }
        else
        {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new RitualOfProtectionPower(player, DEX_GAIN_AMT), DEX_GAIN_AMT));
        }
    }
}
