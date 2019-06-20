package com.halas.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.halas.service.soap package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DuplicateCopterIdException_QNAME = new QName("http://soap.service.halas.com/", "DuplicateCopterIdException");
    private final static QName _NoSuchCopterIdException_QNAME = new QName("http://soap.service.halas.com/", "NoSuchCopterIdException");
    private final static QName _Copter_QNAME = new QName("http://soap.service.halas.com/", "copter");
    private final static QName _MaximumDistanceExceededException_QNAME = new QName("http://soap.service.halas.com/", "MaximumDistanceExceededException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.halas.service.soap
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MaximumDistanceExceededException }
     */
    public MaximumDistanceExceededException createMaximumDistanceExceededException() {
        return new MaximumDistanceExceededException();
    }

    /**
     * Create an instance of {@link NoSuchCopterIdException }
     */
    public NoSuchCopterIdException createNoSuchCopterIdException() {
        return new NoSuchCopterIdException();
    }

    /**
     * Create an instance of {@link DuplicateCopterIdException }
     */
    public DuplicateCopterIdException createDuplicateCopterIdException() {
        return new DuplicateCopterIdException();
    }

    /**
     * Create an instance of {@link Copter }
     */
    public Copter createCopter() {
        return new Copter();
    }

    /**
     * Create an instance of {@link CopterArray }
     */
    public CopterArray createCopterArray() {
        return new CopterArray();
    }

    /**
     * Create an instance of {@link Position }
     */
    public Position createPosition() {
        return new Position();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DuplicateCopterIdException }{@code >}}
     */
    @XmlElementDecl(namespace = "http://soap.service.halas.com/", name = "DuplicateCopterIdException")
    public JAXBElement<DuplicateCopterIdException> createDuplicateCopterIdException(DuplicateCopterIdException value) {
        return new JAXBElement<DuplicateCopterIdException>(_DuplicateCopterIdException_QNAME, DuplicateCopterIdException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoSuchCopterIdException }{@code >}}
     */
    @XmlElementDecl(namespace = "http://soap.service.halas.com/", name = "NoSuchCopterIdException")
    public JAXBElement<NoSuchCopterIdException> createNoSuchCopterIdException(NoSuchCopterIdException value) {
        return new JAXBElement<NoSuchCopterIdException>(_NoSuchCopterIdException_QNAME, NoSuchCopterIdException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Copter }{@code >}}
     */
    @XmlElementDecl(namespace = "http://soap.service.halas.com/", name = "copter")
    public JAXBElement<Copter> createCopter(Copter value) {
        return new JAXBElement<Copter>(_Copter_QNAME, Copter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MaximumDistanceExceededException }{@code >}}
     */
    @XmlElementDecl(namespace = "http://soap.service.halas.com/", name = "MaximumDistanceExceededException")
    public JAXBElement<MaximumDistanceExceededException> createMaximumDistanceExceededException(MaximumDistanceExceededException value) {
        return new JAXBElement<MaximumDistanceExceededException>(_MaximumDistanceExceededException_QNAME, MaximumDistanceExceededException.class, null, value);
    }

}
