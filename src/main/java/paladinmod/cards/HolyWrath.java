package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;
import paladinmod.*;
import paladinmod.patches.*;

public class HolyWrath extends AbstractPaladinCard
{
    private static final String      ID                = "HolyWrath";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 2;
    private static final int         DMG_AMT           = 0;
    private static final int         DIV_BONUS_AMT     = 3;
    private static final int         BON_UPGRADE_AMT   = 2;
    private static final CardType    TYPE              = CardType.ATTACK;
    private static final CardRarity  RARITY            = CardRarity.BASIC;
    private static final CardTarget  TARGET            = CardTarget.ENEMY;

    public HolyWrath()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET);
        this.baseDamage = DMG_AMT;
        this.magicNumber = this.baseMagicNumber = DIV_BONUS_AMT;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new Smite();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeMagicNumber(BON_UPGRADE_AMT);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        // TODO: Code DivinityPower actions
        AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(player, this.damage, this.damageTypeForTurn)));
    }
}
