package org.tdsast.joinus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tdsast.joinus.model.request.InstallRequest;
import org.tdsast.joinus.model.response.Response;
import org.tdsast.joinus.model.response.SiteInfoResponseData;
import org.tdsast.joinus.service.ConfigurationService;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
@RequestMapping("/site")
public class SiteController {
    private final ConfigurationService configurationService;

    @Inject
    public SiteController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @GetMapping("/info")
    public Response<SiteInfoResponseData> getSite() {
        boolean installed = configurationService.isInstalled();
        if (!installed) {
            return Response.failure(new SiteInfoResponseData(false, null, null), "未安装", 50000);
        }
        String siteName = configurationService.getSiteName();
        String shortName = configurationService.getShortName();
        return Response.success(new SiteInfoResponseData(true, siteName, shortName));
    }

    @PostMapping("/install")
    public Response<SiteInfoResponseData> install(@RequestBody @Valid InstallRequest request) {
        if (configurationService.isInstalled()) {
            throw new IllegalStateException("Site is already installed");
        }
        configurationService.install(request.getSiteName(), request.getShortName(), request.getAdmin(), request.getPassword());
        return getSite();
    }
}
