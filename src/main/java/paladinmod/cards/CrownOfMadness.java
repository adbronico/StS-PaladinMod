package paladinmod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.powers.DivinityPower;

public class CrownOfMadness extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:CrownOfMadness";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 3;
    private static final int         UPGRADED_COST     = 2;
    private static final int         STUN_AMT          = 1;
    private static final CardType    TYPE              = CardType.SKILL;
    private static final CardRarity  RARITY            = CardRarity.RARE;
    private static final CardTarget  TARGET            = CardTarget.ENEMY;

    public CrownOfMadness()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.magicNumber = this.baseMagicNumber = STUN_AMT;
        this.exhaust = true;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new CrownOfMadness();
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
        if(player.hasPower(DivinityPower.POWER_ID) && player.getPower(DivinityPower.POWER_ID).amount < 0)
        {
            AbstractDungeon.actionManager.addToBottom(new StunMonsterAction(monster, player, this.magicNumber));
        }
    }
}
