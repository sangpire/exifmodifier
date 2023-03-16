package kr.sangpire.libs

import com.drew.imaging.ImageMetadataReader
import com.drew.metadata.exif.ExifIFD0Directory
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import java.io.File
import java.net.URL
import java.util.Date

class MetadataExtractorSpec : ExpectSpec({
    context("The Image File") {
        val imageFileUrl: URL? = javaClass.getResource("/05400038.JPG")

        expect("Exist!") {
            imageFileUrl.shouldNotBeNull()
        }

        expect("Somethings") {
            val imageFile = File(imageFileUrl!!.path)
            val metadata = ImageMetadataReader.readMetadata(imageFile)

            val exifIFD0 : ExifIFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory::class.java)

            val dateTime: Date? = exifIFD0.getDate(ExifIFD0Directory.TAG_DATETIME)

            println(dateTime)

            exifIFD0.setDate(ExifIFD0Directory.TAG_DATETIME, Date())

            println(dateTime)
        }
    }
})
