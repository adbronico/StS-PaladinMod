package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import paladinmod.PaladinMod;
import paladinmod.actions.ModifyStrengthAction;
import paladinmod.powers.FlamingSwordPower;

public class FlamingSword extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:FlamingSword";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      IMAGE             = "cards/FlamingSword";
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 0;
    private static final int         STR_AMT           = 2;
    private static final int         UPGRADE_STR_ADD   = 1;
    private static final int         DMG_BACK_AMT      = 3;
    private static final int         DMG_BACK_ADD      = 1;
    private static final CardType    TYPE              = CardType.SKILL;
    private static final CardRarity  RARITY            = CardRarity.COMMON;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public FlamingSword()
    {
        super(ID, NAME, PaladinMod.makePath(IMAGE), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.magicNumber = this.baseMagicNumber = STR_AMT;
        this.damage = this.baseDamage = DMG_BACK_AMT;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new FlamingSword();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_STR_ADD);
            this.upgradeDamage(DMG_BACK_ADD);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new ModifyStrengthAction(player, this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new LoseStrengthPower(player, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new FlamingSwordPower(player, this.magicNumber), this.magicNumber));
    }
}
