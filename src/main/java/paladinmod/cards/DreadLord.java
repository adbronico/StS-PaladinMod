package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import paladinmod.PaladinMod;
import paladinmod.actions.ModifyStrengthAction;
import paladinmod.powers.DivinityPower;

public class DreadLord extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:DreadLord";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      IMAGE             = "cards/DreadLord";
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 1;
    private static final int         STR_AMT           = 1;
    private static final int         UPGRADE_STR_ADD   = 1;
    private static final CardType    TYPE              = CardType.SKILL;
    private static final CardRarity  RARITY            = CardRarity.COMMON;
    private static final CardTarget  TARGET            = CardTarget.ENEMY;

    public DreadLord()
    {
        super(ID, NAME, PaladinMod.makePath(IMAGE), COST, DESCRIPTION, TYPE, RARITY, TARGET, true);
        this.magicNumber = this.baseMagicNumber = STR_AMT;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new DreadLord();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_STR_ADD);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        if(player.hasPower(DivinityPower.POWER_ID))
        {
            int divinityAmount = player.getPower(DivinityPower.POWER_ID).amount;
            if(divinityAmount < 0)
            {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, player, new WeakPower(monster, -divinityAmount, false), -divinityAmount));
            }
        }

        AbstractDungeon.actionManager.addToBottom(new ModifyStrengthAction(player, this.magicNumber));
    }
}
