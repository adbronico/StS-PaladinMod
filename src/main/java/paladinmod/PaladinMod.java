package paladinmod;

import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaladinMod implements
        EditCharactersSubscriber,
        EditRelicsSubscriber,
        EditCardsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        OnCardUseSubscriber,
        OnPowersModifiedSubscriber,
        PostBattleSubscriber,
        PostDungeonInitializeSubscriber,
        PostExhaustSubscriber,
        PostDrawSubscriber
{
    public static final Logger logger = LogManager.getLogger(PaladinMod.class.getName());
    public static final Color GOLD = CardHelper.getColor(0.0f, 0.0f, 0.0f);

    public static final String ASSET_FOLDER = "img";

    @Override
    public void receiveEditCards()
    {

    }

    @Override
    public void receiveEditCharacters()
    {

    }

    @Override
    public void receiveEditKeywords()
    {

    }

    @Override
    public void receiveEditRelics()
    {

    }

    @Override
    public void receiveEditStrings()
    {

    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard)
    {

    }

    @Override
    public void receivePowersModified()
    {

    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom)
    {

    }

    @Override
    public void receivePostDraw(AbstractCard abstractCard)
    {

    }

    @Override
    public void receivePostDungeonInitialize()
    {

    }

    @Override
    public void receivePostExhaust(AbstractCard abstractCard)
    {

    }

    public static final String makePath(String fileName)
    {
        String path = ASSET_FOLDER + fileName;

        if(!hasExtension(fileName))
        {
            path += ".png";
        }

        return path;
    }

    public static boolean hasExtension(String fileName)
    {
        return fileName.lastIndexOf('.') > 0;
    }
}
