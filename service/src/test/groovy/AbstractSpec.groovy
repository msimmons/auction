import net.contrapt.auction.service.BidderService
import org.springframework.context.annotation.Configuration
import spock.lang.Specification
/**
 * Created by msimmons on 2/20/15.
 */
@Configuration
class AbstractSpec extends Specification {

    def BidderRepository bidderRepository = Mock(BidderRepository)
    def BidderService bidderService = new BidderService.Impl(bidderRepository: bidderRepository)

}
