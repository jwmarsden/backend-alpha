package co.cata.repository

import co.cata.entity.Asset
import org.springframework.data.repository.CrudRepository

interface AssetRepository : CrudRepository<Asset, Int> {
}