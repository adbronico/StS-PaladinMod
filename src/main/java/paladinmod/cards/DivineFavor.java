package paladinmod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import paladinmod.PaladinMod;
import paladinmod.actions.LoseDivinityAction;
import paladinmod.powers.DivinityPower;

public class DivineFavor extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:DivineFavor";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 1;
    private static final int         DRAW_AMT          = 3;
    private static final int         UPGRADE_DRAW_AMT  = 1;
    private static final int         DIV_LOSS_AMT      = 3;
    private static final CardType    TYPE              = CardType.SKILL;
    private static final CardRarity  RARITY            = CardRarity.COMMON;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public DivineFavor()
    {
        super(ID, NAME, PaladinMod.makePath(ID), COST, DESCRIPTION, TYPE, RARITY, TARGET, false);
        this.baseMagicNumber = this.magicNumber = DRAW_AMT;
        this.divinity = this.baseDivinity = DIV_LOSS_AMT;
    }

    @Override
    public boolean canUse(AbstractPlayer player, AbstractMonster monster)
    {
        boolean canUse = super.canUse(player, monster);

        if(player.hasPower(DivinityPower.POWER_ID) && player.getPower(DivinityPower.POWER_ID).amount < 0)
        {
            //TODO: decide if having positive Divinity is required, or if it is only required for the card draw.
            //canUse = false;
        }

        return canUse;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new DivineFavor();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_DRAW_AMT);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        if(player.hasPower(DivinityPower.POWER_ID))
        {
            int divinityAmount = player.getPower(DivinityPower.POWER_ID).amount;
            if(divinityAmount > 0)
            {
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(player, this.magicNumber));
            }
        }
        AbstractDungeon.actionManager.addToBottom(new LoseDivinityAction(this.divinity));
    }
}
