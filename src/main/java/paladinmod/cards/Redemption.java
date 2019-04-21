package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.actions.ResetDivinityAction;
import paladinmod.powers.RedemptionPower;

public class Redemption extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:Redemption";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      IMAGE             = "cards/Redemption";
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 2;
    private static final int         UPGRADED_COST     = 1;
    private static final CardType    TYPE              = CardType.POWER;
    private static final CardRarity  RARITY            = CardRarity.RARE;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public Redemption()
    {
        super(ID, NAME, PaladinMod.makePath(IMAGE), COST, DESCRIPTION, TYPE, RARITY, TARGET, true);
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new Redemption();
    }

    @Override
    public void upgrade()
    {
        if(!upgraded)
        {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADED_COST);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new ResetDivinityAction());
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new RedemptionPower(player)));
    }
}
