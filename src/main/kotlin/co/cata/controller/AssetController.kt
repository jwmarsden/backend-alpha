package co.cata.controller

import co.cata.dto.AssetDTO
import co.cata.service.AssetService
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/asset")
class AssetController(val assetService: AssetService) {

    companion object : KLogging()

    @GetMapping("/version")
    fun getServiceVersion(): String {
        logger.info("Version Query")
        return assetService.getVersion()
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun createAsset(@RequestBody assetDTO: AssetDTO) : AssetDTO {
        logger.info("Create Asset: $assetDTO")
        val responseAssetDTO = assetService.createAsset(assetDTO)
        logger.info("Created Asset: $responseAssetDTO")
        return responseAssetDTO
    }
}