/**
 * Created by msimmons on 2/20/15.
 */
class BidderServiceSpec extends AbstractSpec {

    def "Get a list of bidder summaries" () {

        when:
        1 * bidderRepository.findAllSummary() >> [new BidderSummary(1L, "Summary1", BigDecimal.ONE, BigDecimal.TEN)]
        def summaries = bidderService.getBidderSummaries()

        then:
        println summaries
        summaries.size() == 1
    }

    def "Get a bidder by id" () {

        when:
        1 * bidderRepository.findOne(_) >> new Bidder("Mark")
        def bidder = bidderService.getBidder(1L)

        then:
        println bidder
        bidder.name == 'Mark'
    }
}
