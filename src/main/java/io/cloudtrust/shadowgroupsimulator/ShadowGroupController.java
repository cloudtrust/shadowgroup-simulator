package io.cloudtrust.shadowgroupsimulator;

import io.cloudtrust.shadowgroupsimulator.exceptions.BadRequestException;
import io.cloudtrust.shadowgroupsimulator.exceptions.NotFoundException;
import io.cloudtrust.shadowgroupsimulator.exceptions.UnauthorizedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller. Will only answer to a GET for path /shadowgroups/usg/{userName}.
 * Exactly replicates behaviour of original asp.net simulator.
 */
@RestController
public class ShadowGroupController {

    @RequestMapping(value="/shadowgroups/usg/{userName}", method=RequestMethod.GET)
    public ShadowGroup getShadowGroup(@PathVariable("userName") String userName,
                                                      @RequestParam(value = "applicationUrl", required = false) String applicationUrl,
                                                      @RequestParam(value = "mobilityStatus", required = false) String mobilityStatus,
                                                      @RequestParam(value = "jobFunctionCode", required = false) String jobFunctionCode,
                                                      @RequestHeader(value = "referer", required = false) String referer,
                                                      @RequestHeader(value = "User-Agent", required = false) List<String> userAgent) {
        ShadowGroup shadowGroup = new ShadowGroup(userName, applicationUrl, mobilityStatus);

        if (referer == null || referer.isEmpty()) {
            throw new UnauthorizedException();
        }

        if ("checkuseragent".equals(userName) && (userAgent == null || userAgent.isEmpty())) {
            throw new BadRequestException();
        }

        if ("notfound".equals(userName)) {
            throw new NotFoundException();
        }

        if ("error".equals(userName)) {
            throw new BadRequestException();
        }

        return shadowGroup;
    }
}
