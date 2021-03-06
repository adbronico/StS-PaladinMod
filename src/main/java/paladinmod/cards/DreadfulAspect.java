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

public class DreadfulAspect extends AbstractPaladinCard
{
    public  static final String      ID                = "PaladinMod:DreadfulAspect";
    private static final CardStrings cardStrings       = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String      NAME              = cardStrings.NAME;
    private static final String      IMAGE             = "cards/DreadfulAspect";
    private static final String      DESCRIPTION       = cardStrings.DESCRIPTION;
    private static final int         COST              = 0;
    private static final int         DIV_AMOUNT        = 1;
    private static final int         DIV_UPGRADE_AMT   = 1;
    private static final int         CARD_DRAW_AMT     = 1;
    private static final CardType    TYPE              = CardType.SKILL;
    private static final CardRarity  RARITY            = CardRarity.COMMON;
    private static final CardTarget  TARGET            = CardTarget.SELF;

    public DreadfulAspect()
    {
        super(ID, NAME, PaladinMod.makePath(IMAGE), COST, DESCRIPTION, TYPE, RARITY, TARGET, true);
        this.divinity = this.baseDivinity = DIV_AMOUNT;
        this.cardDraw = this.baseCardDraw = CARD_DRAW_AMT;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new DreadfulAspect();
    }

    @Override
    public void upgrade()
    {
        if(!this.upgraded)
        {
            this.upgradeName();
            this.upgradeDivinity(DIV_UPGRADE_AMT);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new LoseDivinityAction(this.divinity));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(player, this.cardDraw));
    }
}
