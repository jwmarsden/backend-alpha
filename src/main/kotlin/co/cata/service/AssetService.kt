package co.cata.service

import co.cata.dto.AssetDTO
import co.cata.entity.Asset
import co.cata.repository.AssetRepository
import org.springframework.stereotype.Service

@Service
class AssetService (val assetRepository: AssetRepository) {

    var serviceName: String = AssetService::class.java.simpleName.toString()
    var serviceVersion: String = "1.0.0"

    fun getVersion(): String {
        return "${serviceName}:${serviceVersion}"
    }

    fun createAsset(assetDTO: AssetDTO) : AssetDTO {

        val assetEntity = assetDTO.let {
            Asset(null, it.assetId, it.description)
        }

        assetRepository.save(assetEntity)

        return assetEntity.let {
            AssetDTO(it.id, it.assetId, it.description)
        }
    }
}
