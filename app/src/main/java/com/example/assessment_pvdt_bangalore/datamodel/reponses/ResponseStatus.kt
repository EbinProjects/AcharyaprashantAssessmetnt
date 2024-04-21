import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaCoverage(
	@Json(name = "id") val id: String,
	@Json(name = "title") val title: String,
	@Json(name = "language") val language: String,
	@Json(name = "thumbnail") val thumbnail: Thumbnail,
	@Json(name = "mediaType") val mediaType: Int,
	@Json(name = "coverageURL") val coverageURL: String,
	@Json(name = "publishedAt") val publishedAt: String,
	@Json(name = "publishedBy") val publishedBy: String,
	@Json(name = "backupDetails") val backupDetails: BackupDetails
)

@JsonClass(generateAdapter = true)
data class Thumbnail(
	@Json(name = "id") val id: String,
	@Json(name = "version") val version: Int,
	@Json(name = "domain") val domain: String,
	@Json(name = "basePath") val basePath: String,
	@Json(name = "key") val key: String,
	@Json(name = "qualities") val qualities: List<Int>,
	@Json(name = "aspectRatio") val aspectRatio: Double
)

@JsonClass(generateAdapter = true)
data class BackupDetails(
	@Json(name = "pdfLink") val pdfLink: String,
	@Json(name = "screenshotURL") val screenshotURL: String
)
