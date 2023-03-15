package br.com.fiap.listinha.rabbitmq


import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import org.slf4j.LoggerFactory

object EmailSender {
    fun enviarEmail(destinatario: String?, assunto: String?, mensagem: String?) {
        val username = "fase4fiaplistinha@hotmail.com" // Coloque aqui o endereço de e-mail que será usado para enviar a mensagem
        val password = "SenhaFiap123" // Coloque aqui a senha do endereço de e-mail

        // Configuração do servidor SMTP do Gmail
        val props = Properties()
        props.put("mail.smtp.auth", "true")
        props.put("mail.smtp.starttls.enable", "true")
        props.put("mail.smtp.host", "smtp.office365.com")
        props.put("mail.smtp.port", "587")

        // Cria uma sessão de e-mail com autenticação
        val session = Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(username, password)
            }
        })
        try {
            // Cria uma mensagem de e-mail
            val logger = LoggerFactory.getLogger(EmailSender.javaClass)
            var recipient = ""
            val message: Message = MimeMessage(session)
            message.setFrom(InternetAddress(username))
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("guilherme-hayashi@hotmail.com"))
            message.subject = "Listinha - Alteração realizada"
            message.setText("A alteração foi realizada com sucesso!")
            logger.info("E-mail enviado para o endereço: {}",)
            // Envia a mensagem de e-mail
            Transport.send(message)
            println("Mensagem enviada com sucesso.")
        } catch (e: MessagingException) {
            throw RuntimeException(e)
        }
    }
}



